	� G   J�       �mo�F�s*��~�I%䴩�$� � u��xEh0M�c_�#�/ؗ�U��1`h�*�`�A�&�6�m��J���=w�s��i��>mR^|���=~�[4G�cyJ��i5�H�j4�5m��I'��p�I\��	%.֪�4�э��\ڕe�z?�2����~pW�ā�C���i�~_�9���������C	���(j�T�PZa�)`HT���ڔm���4�a����y�N��̂B���ɭ�������c��p"��KJ� U�0r��Q�u��b2c����H�l��7K\�d8Vi;��({f#9�Ԛe5�[O����`�♆�T]+�s�x*@��d
��ܲ����l5r;���[T�=�܌����+�����<�9]�,�}��O&,���K������:�/�
 qE��4r�5��8~7�_�JQqZ�=���7� ���l!�`���,���E&!J5dO+'�z�$�Fh��L�C���XԱm�CI3���@����DaP�ky�ݪ�s�J���?`��b�3rc�'�U)�.�8SǮ������vIC%QK��B�,��5}�$����!�M�z�T�[Z���j�m�9�����f��f��ls�e�ԑ��y�[�1�B��Ё�SnuFXt��G #�B��$(���7#����睇E�D�.�x�x�l��K��$e� �e�Na���k���Sma�_��y���hwS�ɬ���v�_�{��`i!X���*61�b#�
OUZS�Jb�}J^+TD����c6���i����D9q�$��4bAC	��O���Ok���z�i�ڥǝ�_ �s�F�,��|y�s�9�&Z�-͝+������X�r�N���G��~��B�!{�r�l8.��q���ǋJ�]=��"����TS"TK)�:��B�����A�D{�cTc1*����Q@c��aZۂ�?H���GuC�cao�a��Χ�6�\�Y��e�?���,�����d����a"�bC����o ���2���kw�*�S�q�,^|��'�ܴ��"��<�<�
ʨ�z2��1\l��%��{���
��2�0	�>>	���C�Qg����qf��&u	��m�)~5�&[5���K��O;�^�N�f�&�����{�u��@����_���
�ɡ
�k��׃��,�]�*�UqQ�T������8Z<8�Q�eS�a�E����~��{] T���hx�� `Ó!��'$�$cy��l��}ks��q�����|�б�;���&���}���o�(TП�Ac;�`^�}��1�x������lu�����{ z��� ��0���o�E�I�u�9q/6m�=��SV/�%�T*��̦�94(5̺:r*|�̧���t�:%0�6���Dn�����+���us�w���Q�0۸{�.�l�	��5)�-����j�zf����R���0�}��]�r���h�n֐Y���#T��=6Ǻ�4a��Znf��**���,�<�29az��vA�N}ز����r3h}�7ִ�El���Y�`	+V����ŭ[-k�Oĳ�X�   x  Lga2/stage/AmpedScript; e start double_sword_and_wing_broken double_sword_and_wing_shot shot (Lkotori/geom/Quaternion;)V broken lockItemScript (ZZ)V
 f m n finishAllConnections
 d p 1
 H 0 3 setShadowState
 d s 3 clearGenerate
 d u 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d w x setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d z {D #/accs/double_sword_and_wing/efx.scm�        ?   ?   ?   ?   C4  ´        mahojinD ./accs/double_sword_and_wing_silver/sword_l.scm�            ?333?333?333C4  ´       isServer ()Z
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
 f � �-        >���� ��D 5/accs/double_sword_and_wing_silver/fx_shoot_sword.scm�            ?�  ?�  ?�  C4  B�      � hit_bb  d�     �    ]� x       ,�>�         clearJudgmentKind
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
+�� �W*�� rW*� �W*� �W� ���            =���=���=���� � changemodel� ,/accs/gorgeous_knife/gorgeous_knife_beam.mot