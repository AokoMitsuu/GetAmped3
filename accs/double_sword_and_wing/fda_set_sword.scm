	� G   C�       �mo�F�s*��~�I%䴩�� � u��xAh0M�c_�#�/ؗ�U��1`h�*�`�A�&�6�m��J���=w�s�4m��6)/�����w�R�X�R3r�G�Ҧ�-d�L�hax7[ĥJ�P�b��HS���ơ=Ym�#,�D�{2px�$�����C	�T�C�[
@\��.%lb6>��)R��Fi����!Q���S�bdR�T�i���uN�!Z����9�[5l��/G�Ǵ��Dr��j�a����`�4F3�d�0M/c�v�Feo��V�p��vjQ��f���[5�[O����`�♆�T]��s�x*@��d
��ܲ�\ZI6�:9��d�_�H�m�ڕ�yR�Zݜ��d_������������y?����닁�H\U�WB�4r��x�8L܉�
(T��Ӷ�B��
x��x��!z҄<z���C�\/,�v��T(Ԑ=���灒��W'4	Li&�s΅���a,ض�R���cg�̌�ת{2�Yuk�U}�b�6�������X�~UJ�<�4�k�o�j`�S��PI��)�P>e�> I)#��r�m�F៞/W���֢涚s[k�%�o��䣙�,��rԔ¢ �wK>fW��:pvʭM��Dt�X�V��E�7� 2���0�}�yX�@H�Y�G�wP�&���JRV� �`Qv�FH	�F ј=���I��5�׮��N/ř̚?�o����,\	����j�*6b!a��T�u5�$�٧Lh��(Q��p�&�>��|���(#'����◦@�!h(��������y����W�4M[���{�+�t���o���ݯ�wo<���D����`���5��KU�É�~��؏]�T�7dov@���![��}�`����2�Qp��@H��c�)��A��h!FP���I���1�������(�1ݝ0�A���Cã�!���w�0���Jb.�,��2	�_V^^���������O0o��x���w ��u	��ʵ;��թ�8�.>TǓxn:�tQ���*�e�iyeTX]�c�0B֓���=hbG�K�S��|��t�	���\��|e������uX���w������i��L�򥕥��K�x����bB����ڽ߻�B�nWy����l���P�J�������E؞yU⪸��7?cc2�NnT�c��hXfQ*cn��:�A �`����%�'� ��d���	�<�X�~;��y��ܞw�nk�m-/:vrG_��D1��O���;�)
�'f��Q0��ľ�ט?_<XYz�]~��tKT��{ zr�M�A�&��\��o�E�&)�a�Ľز�N������K"�Va=V�M�shPj�u�T�:9�J+#�:!tJ`tm2�͉��'7)
VW�����-���,[N�M6_�j�{�̲tNd#�D�r���cꙭb�˕JR�`�-�v�*G��4hì#����G({l��i����	5T��34(�<�2+a^��vA�G}ܶjM��������.���؀�\����U6�p��[�v[�_�^c�   x  Lga2/stage/AmpedScript; e start double_sword_and_wing_broken double_sword_and_wing_shot shot (Lkotori/geom/Quaternion;)V broken lockItemScript (ZZ)V
 f m n finishAllConnections
 d p 1
 H 0 3 setShadowState
 d s 3 clearGenerate
 d u 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d w x setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d z {D #/accs/double_sword_and_wing/efx.scm�        ?   ?   ?   ?   C4  ´        mahojinD '/accs/double_sword_and_wing/sword_l.scm�            ?333?333?333C4  ´       isServer ()Z
 f � � getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f � � '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 getArgv ()Ljava/lang/Object;
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � startScript (Ljava/lang/String;)Z
 � � � mahojin_end getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � length ()F
 � � �@`  
 � � 1�� kotori.geom.Quaternion?5�?5�         Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � Ljava/lang/Math; � round (F)I
 � � �
 f � �C4  C�  �4  	 � R  (FF)F
 � � � min
 � � �B4  ��?�               Lkotori/geom/Quaternion; � clone
 � � �>L��B�   getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � rotate (Lkotori/geom/Quaternion;I)V
 � � ���?5�?5�         	playSound
 d � 3 	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-        >���� ��D ./accs/double_sword_and_wing/fx_shoot_sword.scm�            ?�  ?�  ?�  C4  B�      � hit_bb  d�   �    ]� x       ,�>�         clearJudgmentKind
 f � 1 (Z)V
 f m � end_add f  g 1  �*� oW*� qW*2� rW*� tW*� oW*� vW**}~� y� |W**g� y� |W* ȶ rW*� oW*� ��  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  h 1  *� W�  i 1 �*� �L*� �M*� �N*� oW*� rW,� 
,�� �W+��-��*� �:+� �W+� �8��� * �� rW-� �W*�� W�l+� �� �� ��8*� �� �� ��8��� 
�f8��� 
�f8f8��� �f8� ��� 
�b8+� �8	+� �+� �8

	� �� �� �Zd�8�� #bj8bj�� ��nj8b8�:� �:6� 3�`�jj�b8�� �W-� �W*
� rW�W���6� 6�`�jj8�b�b� �W-� �W*
� rW�W����b�b� �W-� �W*2� rW�� �W-� �W*� W� * �� rW-� -� �W*�� W*�� rW*� �W*� �W�  j k  *i� �W*+� �W*�ֶ �W*� �W�  l 1  H*� �W*� �W*� �L*� �M*� rW,� 
,۶ �W+� 
+�� �W*�� rW*� �W*� �W� ���            =���=���=���� � changemodel� ,/accs/gorgeous_knife/gorgeous_knife_beam.mot