	� G   u�       �VmoE��H����.wg'V�\#�Ih"hZb�R�Pt>�ً�n/{�4V��sZZT	D���(��Q@  ��忰r��fw�qZ/_P����gf����ħx�㨕��[�Ucܪ{�ļrf��1eu)��X-������6���`�(:�\��]e�h@�uN*b]:̍p��딴`��g&r��K�:y��6��_�%9�$��;n�^B����ID�N- F�=N �!���$F�q��	z��W�QG�Ij4yqa�B�_��m��;N�]�{�dw#�c+��|�41sڱJe"m�(��Z��w�y�X��o�m�?(|��47H
�"m��m1�[����b����T�OE�3���H����I꘭cfT��U����y�*�zAP9���6�~�6���e8��uUdI��^�J�B��l����9�>���O��8΃�)YԢ���i�.	�����8��iG�Z���L0�~�m���<��Vc�u���4	����mr�rZ�1u�Z���F������;{v�w����H������ß�;x���Ç"�H�������	��:SXm� �����3:��[I�i����
h�����%�w���<�X-�
���ˑ���C.�8G<` ��)��|��4O�L�p��/���y�(��,h��9ض3=[��~�:�����������������(�����'�8�����>�=1x_�_�����{`��Xf����͒�Ɇ��*�)�x�m�e�m��<UDH"c�lJ�`ϖ���xI��!�2mf�d���[{���븵�&�W.��y�LR�5}X0̉�eNMt���$���>�YC6��m.�ׅ�x5�qM�;��68�L�7Q�qF�Œ�<����)G�9u�P��#gS��rC�F��z��x1����j���ؗ�WzE�3�y?����=�1���$$Gie4�8~��:��V-P<�������冒�`��M���,�)R<ulGwPY2ۖw�k��N�m��h�]Xzc�qmun��|q��0���4gf�&c����,zW=��tNҞv>6����;��9����P���/����_!����7H�?h�)�7d�=ƣ
   f  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_pur2/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
 f x y isServer ()Z
 f { | getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f ~  '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 	playSound
 d � 3 getArgv ()Ljava/lang/Object;
 f � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/KMath; � R2D (F)F
 � � � slopeY2 (F)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/Position; � dir	 � � 	 � R  getDir (Lkotori/geom/Vector3D;)F
 � � � Ljava/lang/Math; � round (F)I
 � � � length ()F
 � � � (FF)F
 � � � min
 � � �B4   getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � �B�   	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � � Lga2/stage/Attack; � startScript (Ljava/lang/String;)Z
 � � �-            ��� �D /accs/psyco_pur2/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� D
��                        �  